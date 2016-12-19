package org.zstack.sdk;

import java.util.HashMap;
import java.util.Map;

public class SyncVolumeSizeAction extends AbstractAction {

    private static final HashMap<String, Parameter> parameterMap = new HashMap<>();

    public static class Result {
        public ErrorCode error;
        public SyncVolumeSizeResult value;
    }

    @Param(required = true, nonempty = false, nullElements = false, emptyString = true, noTrim = false)
    public java.lang.String uuid;

    @Param(required = false)
    public java.util.List systemTags;

    @Param(required = false)
    public java.util.List userTags;

    @Param(required = true)
    public String sessionId;

    public long timeout;
    
    public long pollingInterval;


    public Result call() {
        ApiResult res = ZSClient.call(this);
        Result ret = new Result();
        if (res.error != null) {
            ret.error = res.error;
            return ret;
        }
        
        SyncVolumeSizeResult value = res.getResult(SyncVolumeSizeResult.class);
        ret.value = value == null ? new SyncVolumeSizeResult() : value;
        return ret;
    }

    public void call(final Completion<Result> completion) {
        ZSClient.call(this, new InternalCompletion() {
            @Override
            public void complete(ApiResult res) {
                Result ret = new Result();
                if (res.error != null) {
                    ret.error = res.error;
                    completion.complete(ret);
                    return;
                }
                
                SyncVolumeSizeResult value = res.getResult(SyncVolumeSizeResult.class);
                ret.value = value == null ? new SyncVolumeSizeResult() : value;
                completion.complete(ret);
            }
        });
    }

    Map<String, Parameter> getParameterMap() {
        return parameterMap;
    }

    RestInfo getRestInfo() {
        RestInfo info = new RestInfo();
        info.httpMethod = "GET";
        info.path = "/volumes/{uuid}/actions";
        info.needSession = true;
        info.needPoll = true;
        info.parameterName = "syncVolumeSize";
        return info;
    }

}
