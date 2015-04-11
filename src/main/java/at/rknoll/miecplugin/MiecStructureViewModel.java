package at.rknoll.miecplugin;

import at.rknoll.miecplugin.psi.MiecFile;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecStructureViewModel extends StructureViewModelBase implements
        StructureViewModel.ElementInfoProvider {

    public MiecStructureViewModel(PsiFile psiFile, @Nullable Editor editor) {
        super(psiFile, editor, new MiecStructureViewElement(psiFile));
    }

    @NotNull
    @Override
    public Sorter[] getSorters() {
        return new Sorter[] {Sorter.ALPHA_SORTER};
    }

    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return element instanceof MiecFile;
    }
}
