package org.zstack.sdk;

import java.util.HashMap;
import java.util.Map;

public class GetBackupStorageCapacityAction extends AbstractAction {

    private static final HashMap<String, Parameter> parameterMap = new HashMap<>();

    public static class Result {
        public ErrorCode error;
        public GetBackupStorageCapacityResult value;
    }

    @Param(required = false, nonempty = false, nullElements = false, emptyString = true, noTrim = false)
    public java.util.List zoneUuids;

    @Param(required = false, nonempty = false, nullElements = false, emptyString = true, noTrim = false)
    public java.util.List backupStorageUuids;

    @Param(required = false)
    public boolean all;

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
        
        GetBackupStorageCapacityResult value = res.getResult(GetBackupStorageCapacityResult.class);
        ret.value = value == null ? new GetBackupStorageCapacityResult() : value;
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
                
                GetBackupStorageCapacityResult value = res.getResult(GetBackupStorageCapacityResult.class);
                ret.value = value == null ? new GetBackupStorageCapacityResult() : value;
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
        info.path = "/backup-storage/capacities";
        info.needSession = true;
        info.needPoll = false;
        info.parameterName = "params";
        return info;
    }

}
