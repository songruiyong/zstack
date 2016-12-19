package org.zstack.sdk;

import java.util.HashMap;
import java.util.Map;

public class DeleteVmNicFromSecurityGroupAction extends AbstractAction {

    private static final HashMap<String, Parameter> parameterMap = new HashMap<>();

    public static class Result {
        public ErrorCode error;
        public DeleteVmNicFromSecurityGroupResult value;
    }

    @Param(required = true, nonempty = false, nullElements = false, emptyString = true, noTrim = false)
    public java.lang.String securityGroupUuid;

    @Param(required = true, nonempty = true, nullElements = false, emptyString = true, noTrim = false)
    public java.util.List vmNicUuids;

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
        
        DeleteVmNicFromSecurityGroupResult value = res.getResult(DeleteVmNicFromSecurityGroupResult.class);
        ret.value = value == null ? new DeleteVmNicFromSecurityGroupResult() : value;
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
                
                DeleteVmNicFromSecurityGroupResult value = res.getResult(DeleteVmNicFromSecurityGroupResult.class);
                ret.value = value == null ? new DeleteVmNicFromSecurityGroupResult() : value;
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
        info.path = "/security-groups/{securityGroupUuid}/vm-instances/nics";
        info.needSession = true;
        info.needPoll = true;
        info.parameterName = "";
        return info;
    }

}
