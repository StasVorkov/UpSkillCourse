package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryObserverable implements Repository {

    private final List<WebHook> webHookList = new ArrayList<>();
    private final List<Event> eventList = new ArrayList<>();

    @Override
    public void addWebHook(WebHook webHook) {
        webHookList.add(webHook);
    }

    @Override
    public Commit commit(String branch, String author, String[] changes) {
        Commit commit = new Commit(author, changes);
        Event event = new Event(Event.Type.COMMIT, branch, Collections.singletonList(commit));
        eventList.add(event);
        for (WebHook w : webHookList) {
            w.onEvent(event);
        }
        return commit;
    }

    @Override
    public void merge(final String sourceBranch, final String targetBranch) {
        List<Commit> commitsOnTargetBranch = getCommitsFromBranch(targetBranch);
        List<Commit> commitsOnSourceBranch = getCommitsFromBranch(sourceBranch);
        commitsOnSourceBranch.removeAll(commitsOnTargetBranch);
        if (commitsOnSourceBranch.isEmpty()) {
            return;
        }
        Event event = new Event(Event.Type.MERGE, targetBranch, commitsOnSourceBranch);
        eventList.add(event);
        for (WebHook webHook : webHookList) {
            webHook.onEvent(event);
        }
    }

    private List<Commit> getCommitsFromBranch(final String branch) {
        return eventList.stream()
                .filter(e -> e.branch().equalsIgnoreCase(branch))
                .map(event -> event.commits())
                .flatMap(commits -> commits.stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
