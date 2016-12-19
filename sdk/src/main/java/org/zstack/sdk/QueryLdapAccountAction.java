package org.zstack.sdk;

import java.util.HashMap;
import java.util.Map;

public class QueryLdapAccountAction extends QueryAction {

    private static final HashMap<String, Parameter> parameterMap = new HashMap<>();

    public static class Result {
        public ErrorCode error;
        public QueryLdapAccountResult value;
    }



    public Result call() {
        ApiResult res = ZSClient.call(this);
        Result ret = new Result();
        if (res.error != null) {
            ret.error = res.error;
            return ret;
        }
        
        QueryLdapAccountResult value = res.getResult(QueryLdapAccountResult.class);
        ret.value = value == null ? new QueryLdapAccountResult() : value;
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
                
                QueryLdapAccountResult value = res.getResult(QueryLdapAccountResult.class);
                ret.value = value == null ? new QueryLdapAccountResult() : value;
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
        info.path = "/ldap/accounts/refs";
        info.needSession = true;
        info.needPoll = false;
        info.parameterName = "";
        return info;
    }

}
