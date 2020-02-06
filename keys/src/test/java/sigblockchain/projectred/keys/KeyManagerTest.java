package sigblockchain.projectred.keys;

import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class KeyManagerTest {

    @Test
    public void testGenerateKeyPair() {
        var keyPair = KeyManager.generateKeyPair();
        var privateKey = (ECPrivateKeyParameters) keyPair.getPrivate();
        var actualPublicKey = (ECPublicKeyParameters) keyPair.getPublic();

        ECPoint Q = KeyManager.domainParams.getG().multiply(privateKey.getD());
        var expectedPublicKey = new ECPublicKeyParameters(Q, KeyManager.domainParams);
        assertEquals(actualPublicKey.getQ().getRawXCoord().toBigInteger(), expectedPublicKey.getQ().getRawXCoord().toBigInteger());
        assertEquals(actualPublicKey.getQ().getRawYCoord().toBigInteger(), expectedPublicKey.getQ().getRawYCoord().toBigInteger());

    }


    @Test
    public void testEcParameters() {
        // an example of the P-256 public private key pair.
        var expectedPrivateKey = new BigInteger("6548d29d5533079e28d5c0edecb6394ed4c0342bfa475fdc96c7bf978492f23d", 16);
        var expectedPublicKeyX = new BigInteger("fac843c2ccf0246eb00cb90e62dd96c64b669e2b867c0c2aaa835801aeae8295", 16);
        var expectedPublicKeyY = new BigInteger("a797eb07a5d8eef80d5705a7a627c73cf4e683ab98c7245101025be935c4053b", 16);

        var fpMultiplier = new FixedPointCombMultiplier();
        ECPoint Q = fpMultiplier.multiply(KeyManager.domainParams.getG(), expectedPrivateKey);

        var actualPublicKey = new ECPublicKeyParameters(Q, KeyManager.domainParams);
        var actualPrivateKey = new ECPrivateKeyParameters(expectedPrivateKey, KeyManager.domainParams);

        assertEquals(actualPrivateKey.getD(), expectedPrivateKey);
        assertEquals(actualPublicKey.getQ().getRawXCoord().toBigInteger(), expectedPublicKeyX);
        assertEquals(actualPublicKey.getQ().getRawYCoord().toBigInteger(), expectedPublicKeyY);

    }

    public boolean streamsAreTheSame(InputStream is1, InputStream is2) throws IOException {
        if ((is1 == null && is2 != null) || (is1 != null && is2 == null))
            return false;
        if (is1 == is2)
            return true;
        var actualBuffer = new byte[1024];
        var expectedBuffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = is1.read(actualBuffer)) != -1) {
            if (is2.read(expectedBuffer) != bytesRead)
                return false;
            if (!Arrays.equals(expectedBuffer, 0, bytesRead, actualBuffer, 0, bytesRead))
                return false;
        }
        return true;

    }

    @Test
    public void testSavePrivateKey1() {
        var d = new BigInteger("e66614b9d32c2c9cc00bde7751d0b50e395c486181deb5a07acffff5fffeaacf", 16);
        var privateKey = new ECPrivateKeyParameters(d, KeyManager.domainParams);
        KeyManager.savePrivateKeyToFile(privateKey, "key");

        try {
            InputStream actualIn = KeyManager.class.getClassLoader().getResourceAsStream("key.pem");
            InputStream expectedIn = ClassLoader.getSystemClassLoader().getResourceAsStream("tetsKey.pem");
            if (actualIn == null || expectedIn == null) {
                fail("Failed to get resource");
            }

            assertTrue(streamsAreTheSame(actualIn, expectedIn), "File contents are not the same");
        } catch (IOException e) {
            e.printStackTrace();
            fail("failed to open file(s)");
        }
    }

    @Test
    public void testSavePrivateKey2() {
        var d = new BigInteger("3775c53962cc507ced257b089031db955bd433b5fd6cfcde0351b8762d5d6244", 16);
        var privateKey = new ECPrivateKeyParameters(d, KeyManager.domainParams);
        KeyManager.savePrivateKeyToFile(privateKey, "key2"); //the file should not be

        try {
            InputStream actualIn = KeyManager.class.getClassLoader().getResourceAsStream("key2.pem");
            InputStream expectedIn = ClassLoader.getSystemClassLoader().getResourceAsStream("tetsKey2.pem");
            if (actualIn == null || expectedIn == null) {
                fail("Failed to get resource");
            }

            assertTrue(streamsAreTheSame(actualIn, expectedIn), "File contents are not the same");
        } catch (IOException e) {
            e.printStackTrace();
            fail("failed to open file(s)");
        }
    }
}
