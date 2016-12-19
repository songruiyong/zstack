package org.zstack.sdk;

import java.util.HashMap;
import java.util.Map;

public class GetVolumeCapabilitiesAction extends AbstractAction {

    private static final HashMap<String, Parameter> parameterMap = new HashMap<>();

    public static class Result {
        public ErrorCode error;
        public GetVolumeCapabilitiesResult value;
    }

    @Param(required = true, nonempty = false, nullElements = false, emptyString = true, noTrim = false)
    public java.lang.String uuid;

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
        
        GetVolumeCapabilitiesResult value = res.getResult(GetVolumeCapabilitiesResult.class);
        ret.value = value == null ? new GetVolumeCapabilitiesResult() : value;
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
                
                GetVolumeCapabilitiesResult value = res.getResult(GetVolumeCapabilitiesResult.class);
                ret.value = value == null ? new GetVolumeCapabilitiesResult() : value;
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
        info.path = "/volumes/{uuid}/capabilities";
        info.needSession = true;
        info.needPoll = false;
        info.parameterName = "";
        return info;
    }

}
