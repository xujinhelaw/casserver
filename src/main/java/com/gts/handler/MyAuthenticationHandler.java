/**
 * 本地已有的项目提交到git的操作
 * git init
 * 添加.gitignore文件
 *  git add .
 *  git commit -m"xxxx"
 *  git remote add origin https://github.com/xxx/xx.git
 *  git --rebase origin master
 *  git push origin master
 */
package com.gts.handler;

import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;

import javax.security.auth.login.AccountNotFoundException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

public class MyAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {
    public MyAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential, String originalPassword) throws GeneralSecurityException, PreventedException {

        if ("admin".equals(credential.getUsername())) {
            return createHandlerResult(credential,
                    this.principalFactory.createPrincipal(credential.getUsername()),
                    new ArrayList<>(0));
        } else {
            throw new AccountNotFoundException("必须是admin用户才允许通过");
        }
    }

}
