package com.example.common.http;import org.json.JSONObject;public class TaskListenerAdapter implements TaskListener {    @Override    public void taskStarted(HttpHelper.TaskType type) {    }    @Override    public void taskError(HttpHelper.TaskType type, ApiError error) {    }    @Override    public void taskFinished(HttpHelper.TaskType type, JSONObject object) {    }    @Override    public void taskFinished(HttpHelper.TaskType type, HttpItem item) {    }}