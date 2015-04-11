package at.rknoll.miecplugin;

import com.intellij.lang.Language;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecLanguage extends Language {
    public static final MiecLanguage INSTANCE = new MiecLanguage();

    private MiecLanguage() {
        super("Miec");
    }
}
