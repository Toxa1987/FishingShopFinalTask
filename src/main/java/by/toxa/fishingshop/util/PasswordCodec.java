package by.toxa.fishingshop.util;

import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordCodec {
    private static PasswordCodec instance = new PasswordCodec();
    ;

    private PasswordCodec() {
    }

    public static PasswordCodec getInstance() {
        return instance;
    }

    public String codeString(String password, String sol) {
        String forCode = password + sol;
        return DigestUtils.md5Hex(forCode);
    }
}
