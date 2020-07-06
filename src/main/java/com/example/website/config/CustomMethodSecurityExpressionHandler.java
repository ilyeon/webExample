package com.example.website.config;

import com.example.website.mapper.BoardMapper;
import com.example.website.mapper.CommentMapper;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler
{
		private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

		@Autowired
		BoardMapper boardMapper;

		@Autowired
		CommentMapper commentMapper;

		@Override
		protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
			Authentication authentication, MethodInvocation invocation) {
				CustomMethodSecurityExpressionRoot root = 
					new CustomMethodSecurityExpressionRoot(authentication);
				root.setPermissionEvaluator(getPermissionEvaluator());
				root.setTrustResolver(this.trustResolver);
				root.setRoleHierarchy(getRoleHierarchy());

				root.setBoardMapper(boardMapper);
				root.setCommentMapper(commentMapper);
				return root;
		}
}