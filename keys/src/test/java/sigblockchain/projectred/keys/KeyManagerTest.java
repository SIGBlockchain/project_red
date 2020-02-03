package sigblockchain.projectred.keys;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.SecureRandom;

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
                ecp.getG(), ecp.getN(), ecp.getH(),
                ecp.getSeed());

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
}
