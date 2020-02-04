package sigblockchain.projectred.keys;

import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.math.ec.WNafUtil;
import org.bouncycastle.util.BigIntegers;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.math.ec.ECPoint;


class KeyManagerTest {

//    @Test
//    void testGeneratePrivateKey() {
//        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
//        KeyPairGenerator g = null;
//        try {
//            g = KeyPairGenerator.getInstance("EC");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        try {
//            g.initialize(ecSpec, new SecureRandom());
//        } catch (InvalidAlgorithmParameterException e) {
//            e.printStackTrace();
//        }
//        KeyPair keypair = g.generateKeyPair();
//        PublicKey publicKey = keypair.getPublic();
//        PrivateKey privateKey = keypair.getPrivate();
//
//    }

    public static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    @Test
    void testGeneratePrivateKey2() {
        // Get domain parameters for example curve secp256r1
        X9ECParameters ecp = SECNamedCurves.getByName("secp256r1");
        ECDomainParameters domainParams = new ECDomainParameters(ecp.getCurve(),
                ecp.getG(), ecp.getN(), ecp.getH(), ecp.getSeed());

        // Generate a private key and a public key
        AsymmetricCipherKeyPair keyPair;
        ECKeyGenerationParameters keyGenParams = new ECKeyGenerationParameters(domainParams, new SecureRandom());
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(keyGenParams);
        keyPair = generator.generateKeyPair();

        ECPrivateKeyParameters privateKey = (ECPrivateKeyParameters) keyPair.getPrivate();
        ECPublicKeyParameters publicKey = (ECPublicKeyParameters) keyPair.getPublic();
        byte[] privateKeyBytes = privateKey.getD().toByteArray();


        // First print our generated private key and public key
        System.out.println("Private key: " + toHex(privateKeyBytes));
        System.out.println("Public key (Compressed): " + toHex(publicKey.getQ().getEncoded(true)));

        // Then calculate the public key only using domainParams.getG() and private key
        ECPoint Q = domainParams.getG().multiply(new BigInteger(privateKeyBytes));
        System.out.println("Calculated public key: " + toHex(Q.getEncoded(true)));

//        // The calculated public key and generated public key should always match
//        if (!toHex(publicKey.getQ().getEncoded(true)).equals(toHex(Q.getEncoded(true)))) {
//            System.out.println("ERROR: Public keys do not match!");
//        } else {
//            System.out.println("Congratulations, public keys match!");
//        }
    }

    @Test
    void testGeneratePrivateKey3() {
        // private key: 0x564632e0c0b640973222cd2130a0142f8d681e86f832ccecb3ef7128c553b9c1
        // public key: 0xb4d04214fc2be35a4740f15521ea7dcefb4a802a16200fb6bcfd2050be49d90adec09ca4df30e8471bfff3a044a96400f46adf0ca6c0118c98d9a85331bfadf8

        X9ECParameters ecp = SECNamedCurves.getByName("secp256r1");
        var domainParams = new ECDomainParameters(ecp.getCurve(),
                ecp.getG(), ecp.getN(), ecp.getH(), ecp.getSeed());

        System.out.printf("secp256r1 parameters: \nG: %s \nN: 0x%s \nH: %s \nSeed: %s\n", ecp.getG().toString(), ecp.getN().toString(16),
                ecp.getH().toString(16), Arrays.toString(ecp.getSeed()));


        var n = new BigInteger("564632e0c0b640973222cd2130a0142f8d681e86f832ccecb3ef7128c553b9c1", 16);
        System.out.printf("Imported private key: \n%s\n", n.toString(16));


        var fpMultiplier = new FixedPointCombMultiplier();
        ECPoint Q = fpMultiplier.multiply(domainParams.getG(), n);
//
        var publicKey = new ECPublicKeyParameters(Q, domainParams);
        var privateKey = new ECPrivateKeyParameters(n, domainParams);

        System.out.printf("Private key: %s\n", privateKey.getD().toString(16));
        System.out.printf("Public key: %s%s\n", publicKey.getQ().getRawXCoord().toBigInteger().toString(16), publicKey.getQ().getRawYCoord().toBigInteger().toString(16));

    }
}
