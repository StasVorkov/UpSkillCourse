package com.epam.rd.autocode.observer.git;

public class Main {
    public static void main(String[] args) {

        Repository repo = GitRepoObservers.newRepository();

        WebHook wh1 = GitRepoObservers.commitToBranchWebHook("master");
        WebHook wh2 = GitRepoObservers.commitToBranchWebHook("master-stale");
        WebHook wh3 = GitRepoObservers.mergeToBranchWebHook("master");

        repo.addWebHook(wh1);
        repo.addWebHook(wh2);
        repo.addWebHook(wh3);

        repo.commit("master", "Den", new String[]{"init", "change1"});

        System.out.println(wh1.caughtEvents());
    }
}
