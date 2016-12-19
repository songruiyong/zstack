package org.zstack.sdk;

import java.util.HashMap;
import java.util.Map;

public class GetVolumeFormatAction extends AbstractAction {

    private static final HashMap<String, Parameter> parameterMap = new HashMap<>();

    public static class Result {
        public ErrorCode error;
        public GetVolumeFormatResult value;
    }

    @Param(required = false)
    public java.util.List systemTags;

    @Param(required = false)
    public java.util.List userTags;

    @Param(required = true)
    public String sessionId;


    public Result call() {
        ApiResult res = ZSClient.call(this);
        Result ret = new Result();
        if (res.error != null) {
            ret.error = res.error;
            return ret;
        }
        
        GetVolumeFormatResult value = res.getResult(GetVolumeFormatResult.class);
        ret.value = value == null ? new GetVolumeFormatResult() : value;
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
                
                GetVolumeFormatResult value = res.getResult(GetVolumeFormatResult.class);
                ret.value = value == null ? new GetVolumeFormatResult() : value;
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
        info.path = "/volumes/formats";
        info.needSession = true;
        info.needPoll = false;
        info.parameterName = "";
        return info;
    }

}
