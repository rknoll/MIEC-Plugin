package at.rknoll.miecplugin;

import at.rknoll.miecplugin.psi.MiecProperty;
import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecChooseByNameContributor implements ChooseByNameContributor {
    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        List<MiecProperty> properties = MiecUtil.findProperties(project);
        List<String> names = new ArrayList<>(properties.size());
        for (MiecProperty property : properties) {
            if (property.getKey() != null && property.getKey().length() > 0) {
                names.add(property.getKey());
            }
        }
        return names.toArray(new String[names.size()]);
    }

    @NotNull
    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        // todo include non project items
        List<MiecProperty> properties = MiecUtil.findProperties(project, name);
        return properties.toArray(new NavigationItem[properties.size()]);
    }
}
