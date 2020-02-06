package sigblockchain.projectred.keys;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;

import java.security.SecureRandom;

public class KeyManager {

    public static final X9ECParameters ecp = SECNamedCurves.getByName("secp256r1");
    public static final ECDomainParameters domainParams = new ECDomainParameters(ecp.getCurve(),
            ecp.getG(), ecp.getN(), ecp.getH(), ecp.getSeed());

    /**
     * Will generate a public/private key pair according to the NIST P-256 standards.
     *
     * @return a AsymmetricCipherKeyPair object
     */
    public static AsymmetricCipherKeyPair generateKeyPair() {
        ECKeyGenerationParameters keyGenParams = new ECKeyGenerationParameters(domainParams, new SecureRandom());
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(keyGenParams);
        return generator.generateKeyPair();
    }

    /**
     *  Save a private EC key in a file, PEM encoded. The file will be stored within the resources directory
     * @param privateKey
     * @param name the name assigned to the key. Can be used to retrieve the key later on. The name will be used to save the key to the file &lt name&gt .pem
     */
    public static void savePrivateKeyToFile(ECPrivateKeyParameters privateKey, String name) {

    }
}
