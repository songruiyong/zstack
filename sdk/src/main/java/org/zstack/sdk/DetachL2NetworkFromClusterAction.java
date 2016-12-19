package org.zstack.sdk;

import java.util.HashMap;
import java.util.Map;

public class DetachL2NetworkFromClusterAction extends AbstractAction {

    private static final HashMap<String, Parameter> parameterMap = new HashMap<>();

    public static class Result {
        public ErrorCode error;
        public DetachL2NetworkFromClusterResult value;
    }

    @Param(required = true, nonempty = false, nullElements = false, emptyString = true, noTrim = false)
    public java.lang.String l2NetworkUuid;

    @Param(required = true, nonempty = false, nullElements = false, emptyString = true, noTrim = false)
    public java.lang.String clusterUuid;

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
        
        DetachL2NetworkFromClusterResult value = res.getResult(DetachL2NetworkFromClusterResult.class);
        ret.value = value == null ? new DetachL2NetworkFromClusterResult() : value;
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
                
                DetachL2NetworkFromClusterResult value = res.getResult(DetachL2NetworkFromClusterResult.class);
                ret.value = value == null ? new DetachL2NetworkFromClusterResult() : value;
                completion.complete(ret);
            }
        });
    }

    Map<String, Parameter> getParameterMap() {
        return parameterMap;
    }

    RestInfo getRestInfo() {
        RestInfo info = new RestInfo();
        info.httpMethod = "DELETE";
        info.path = "/l2-networks/{l2NetworkUuid}/clusters/{clusterUuid}";
        info.needSession = true;
        info.needPoll = true;
        info.parameterName = "null";
        return info;
    }

}
