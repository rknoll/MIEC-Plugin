package at.rknoll.miecplugin;

import at.rknoll.miecplugin.psi.MiecElementFactory;
import at.rknoll.miecplugin.psi.MiecFile;
import at.rknoll.miecplugin.psi.MiecProperty;
import at.rknoll.miecplugin.psi.MiecTypes;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by rknoll on 11/04/15.
 */
public class CreatePropertyQuickFix extends BaseIntentionAction {
    private String key;

    CreatePropertyQuickFix(String key) {
        this.key = key;
    }

    @NotNull
    @Override
    public String getText() {
        return "Create property";
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "Miec properties";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
        return true;
    }

    @Override
    public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
        ApplicationManager.getApplication().invokeLater(() -> {
            final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor(MiecFileType.INSTANCE);
            descriptor.setRoots(project.getBaseDir());
            final VirtualFile file1 = FileChooser.chooseFile(descriptor, project, null);
            if (file1 != null) {
                ApplicationManager.getApplication().runWriteAction(() -> {
                    MiecFile simpleFile = (MiecFile) PsiManager.getInstance(project).findFile(file1);
                    ASTNode lastChildNode = simpleFile.getNode().getLastChildNode();
                    if (lastChildNode != null && !lastChildNode.getElementType().equals(MiecTypes.CRLF)) {
                        simpleFile.getNode().addChild(MiecElementFactory.createCRLF(project).getNode());
                    }
                    MiecProperty property = MiecElementFactory.createProperty(project, key, "");
                    CommandProcessor.getInstance().executeCommand(project, () -> {
                        simpleFile.getNode().addChild(property.getNode());
                        ((Navigatable) property.getLastChild().getNavigationElement()).navigate(true);
                        FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().
                                moveCaretRelatively(2, 0, false, false, false);
                    }, getText(), null);
                });
            }
        });
    }
}
