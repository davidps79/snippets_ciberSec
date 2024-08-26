import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

class DESTest {
    public static void main(String[] args) {
        String test = "1";
        String key = "0101010101010101";
        String[] messages = new String[]{"95F8A5E5DD31D900", "DD7F121CA5015619 ", "2E8653104F3834EA", "4BD388FF6CD81D4F"};
        String[] expected = new String[]{"8000000000000000", "4000000000000000", "2000000000000000", "1000000000000000"};

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
            cf.init(Cipher.ENCRYPT_MODE, ky);
            byte[] theCph = cf.doFinal(theMsg);
            System.out.println("Key     : " + bytesToHex(theKey));
            System.out.println("Message : " + bytesToHex(theMsg));
            System.out.println("Cipher  : " + bytesToHex(theCph));
            System.out.println("Expected: " + bytesToHex(theExp));
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
