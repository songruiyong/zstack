package org.zstack.sdk;

import java.util.HashMap;
import java.util.Map;

public class GetVmBootOrderAction extends AbstractAction {

    private static final HashMap<String, Parameter> parameterMap = new HashMap<>();

    public static class Result {
        public ErrorCode error;
        public GetVmBootOrderResult value;
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
        
        GetVmBootOrderResult value = res.getResult(GetVmBootOrderResult.class);
        ret.value = value == null ? new GetVmBootOrderResult() : value;
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
                
                GetVmBootOrderResult value = res.getResult(GetVmBootOrderResult.class);
                ret.value = value == null ? new GetVmBootOrderResult() : value;
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
        info.path = "/vm-instances/{uuid}/boot-orders";
        info.needSession = true;
        info.needPoll = false;
        info.parameterName = "null";
        return info;
    }

}
