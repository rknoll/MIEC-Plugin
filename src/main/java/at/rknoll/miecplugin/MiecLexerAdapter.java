package at.rknoll.miecplugin;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;

import java.io.Reader;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecLexerAdapter extends FlexAdapter {
    public MiecLexerAdapter() {
        super(new MiecLexer((Reader) null));
    }
}
