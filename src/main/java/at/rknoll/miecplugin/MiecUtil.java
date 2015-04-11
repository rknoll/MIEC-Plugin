package at.rknoll.miecplugin;

import at.rknoll.miecplugin.psi.MiecFile;
import at.rknoll.miecplugin.psi.MiecProperty;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecUtil {
    public static List<MiecProperty> findProperties(Project project, String key) {
        List<MiecProperty> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME,
                MiecFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            MiecFile simpleFile = (MiecFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                MiecProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, MiecProperty.class);
                if (properties != null) {
                    for (MiecProperty property : properties) {
                        if (key.equals(property.getKey())) {
                            result.add(property);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static List<MiecProperty> findProperties(Project project) {
        List<MiecProperty> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME,
                MiecFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            MiecFile simpleFile = (MiecFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                MiecProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, MiecProperty.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }
}
