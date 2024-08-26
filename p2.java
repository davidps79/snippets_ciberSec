import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

class DESTest {
    public static void main(String[] args) {
        String test = "1";
        String key = "0101010101010101";
        String[] expected = new String[]{"20B9E767B2FB1456", "55579380D77138EF", "6CC5DEFAAF04512F", "0D9F279BA5D87260"};
        String[] messages = new String[]{"0800000000000000", "0400000000000000", "0200000000000000", "0100000000000000"};

        for (int i=0; i<4; i++) {
            testVector(key, messages[i], expected[i]);
        }
    }

    public static void testVector(String preKey, String preMsg, String preExp) {
        try {
            byte[] theKey = hexToBytes(preKey);
            byte[] theMsg = hexToBytes(preMsg);
            byte[] theExp = hexToBytes(preExp);

            KeySpec ks = new DESKeySpec(theKey);
            SecretKeyFactory kf
                    = SecretKeyFactory.getInstance("DES");
            SecretKey ky = kf.generateSecret(ks);
            Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
            cf.init(Cipher.DECRYPT_MODE, ky);
            byte[] theCph = cf.doFinal(theMsg);
            System.out.println("Key         : " + bytesToHex(theKey));
            System.out.println("Cipher text : " + bytesToHex(theMsg));
            System.out.println("Clear text  : " + bytesToHex(theCph));
            System.out.println("Expected    : " + bytesToHex(theExp));
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(
                        str.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }
    }

    public static String bytesToHex(byte[] data) {
        if (data == null) {
            return null;
        } else {
            int len = data.length;
            String str = "";
            for (int i = 0; i < len; i++) {
                if ((data[i] & 0xFF) < 16) str = str + "0"
                        + java.lang.Integer.toHexString(data[i] & 0xFF);
                else str = str
                        + java.lang.Integer.toHexString(data[i] & 0xFF);
            }
            return str.toUpperCase();
        }
    }
}
