package eu.ensup.gestionetablissement.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProcessingUrlPath
{
    private static final List<String> TYPES = List.of("student", "course", "mark");
    private static final List<String> ACTIONS = List.of("all", "get", "create", "update", "delete");

    public static HashMap<String, String> convert(String pathUrl)
    {
        HashMap<String, String> hmPath = new HashMap<String, String>();

        List<String> listPath = List.of(pathUrl.substring(1).split("/"));

        String type = listPath.get(0);
        if( ! TYPES.contains(type) && ! TYPES.contains(type.substring(0, type.length()-2)) )
        {
            return hmPath;
        }
        hmPath.put("parent", type);

        if( listPath.size() == 1 ) {
            hmPath.put("action", "all");
            return hmPath;
        }
        else
        {
            for( String value : listPath )
            {
                if( ACTIONS.contains(value) ) {
                    hmPath.put("action", value);
                    listPath.remove(value);
                }
            }
        }

        while( ! listPath.isEmpty() && listPath.get(1).matches("\\d+") )
        {
            hmPath.put(listPath.remove(0), listPath.remove(0));
        }

        if( ! listPath.isEmpty()  )
        {
            System.out.println("listPath :");
            System.out.println(listPath);
        }

        return hmPath;
    }
}
