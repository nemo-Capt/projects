package org.jazzteam.sidorov.onlinepiano;

public class KeyBinds {

    private String keyBind;

    void setKeyBindQwerty() {
        keyBind = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    }

    void setKeyBindDigits() {
        keyBind = "1234567890-=!@#$%^&*()_+";
    }

    public String getKeyBind() {
        return keyBind;
    }

    public void setKeyBind(String keyBind) {
        this.keyBind = keyBind;
    }
}
