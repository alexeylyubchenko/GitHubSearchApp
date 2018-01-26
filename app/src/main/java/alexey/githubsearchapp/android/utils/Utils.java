package alexey.githubsearchapp.android.utils;

/**
 * Created by Alexey on 25/01/18.
 */


import java.util.List;

import alexey.githubsearchapp.android.models.GitHubRepository;
import lombok.Getter;
import lombok.Setter;

public class Utils {

    @Getter @Setter
    private static List<GitHubRepository> gitHubRepositories;

}