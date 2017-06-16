package grupatp.bundles;

import java.util.ListResourceBundle;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class resourceList_lang_en extends ListResourceBundle{

    private static final Object[][] contents = {

            { "First", "Tomasz Stachura 203996"},
            { "Second", "Paweł Ograbek 203956" },
            
    };

    
    @Override
    protected Object[][] getContents() {
        return contents;
    }

}
