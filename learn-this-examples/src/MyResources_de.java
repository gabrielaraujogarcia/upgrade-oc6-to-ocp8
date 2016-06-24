

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//German language
public class MyResources_de extends MyResources {

	public Object handleGetObject(String key) {
		// don't need okKey, since parent level handles it.
		if (key.equals("cancelKey"))
			return "Abbrechen";
		return null;
	}

	protected Set<String> handleKeySet() {
		return new HashSet<String>(Arrays.asList("cancelKey"));
	}
}