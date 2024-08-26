import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.Key;


class DESTest {
    public static void main(String[] args) {

        String key = "00000000000000000000000000000000"; // 128 bits key
        String[] messages = new String[]{"80000000000000000000000000000000", "c0000000000000000000000000000000", "e0000000000000000000000000000000", "f0000000000000000000000000000000"};
        String[] expected = new String[]{"3ad78e726c1ec02b7ebfe92b23d9ec34", "aae5939c8efdf2f04e60b9fe7117b2c2", "f031d4d74f5dcbf39daaf8ca3af6e527", "96d9fd5cc4f07441727df0f33e401a36"};

        for (int i=0; i<4; i++) {
            testVector(key, messages[i], expected[i]);
        }

    }

    public static void testVector(String preKey, String preMsg, String preExp) {
        try {
            byte[] theKey = hexToBytes(preKey);
            byte[] theMsg = hexToBytes(preMsg);
            byte[] theExp = hexToBytes(preExp);

            Key aesKey = new SecretKeySpec(theKey, "AES");

            Cipher cf = Cipher.getInstance("AES/ECB/NoPadding");

            cf.init(Cipher.ENCRYPT_MODE, aesKey);


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