package com.epam.rd.autocode.observer.git;

public class GitRepoObservers {

    public static Repository newRepository() {
        return new RepositoryObserverable();
    }

    public static WebHook mergeToBranchWebHook(String branchName) {
        return new WebHookObserver(Event.Type.MERGE, branchName);
    }

    public static WebHook commitToBranchWebHook(String branchName) {
        return new WebHookObserver(Event.Type.COMMIT, branchName);
    }
}
