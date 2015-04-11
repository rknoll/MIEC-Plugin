package at.rknoll.miecplugin;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecCodeStyleSettings extends CustomCodeStyleSettings {
    public MiecCodeStyleSettings(CodeStyleSettings settings) {
        super("MiecCodeStyleSettings", settings);
    }
}
