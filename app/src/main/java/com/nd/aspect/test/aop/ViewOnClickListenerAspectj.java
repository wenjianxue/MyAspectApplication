package com.nd.aspect.test.aop;

import android.util.Log;
import android.view.View;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Xuewenjian on 2017/8/9 0009.
 */
@Aspect
public class ViewOnClickListenerAspectj {
    private static final String TAG = "ViewOnClickListenerAj";

    @Pointcut("execution(* android.view.View.OnClickListener.onClick(android.view.View))")
    public void pointcutOnClickListener() {}

    /**
     * android.view.View.OnClickListener.onClick(android.view.View)
     *
     * @param joinPoint JoinPoint
     * @throws Throwable Exception
     */
    @Before("pointcutOnClickListener()")
    public void beforePcOnClickListener(final JoinPoint joinPoint) throws Throwable {
        Log.d(TAG,"ViewOnClickListenerAspectj beforePcOnClickListener 方法，在onClick之 前 被调用");
        if (joinPoint == null || joinPoint.getArgs() == null || joinPoint.getArgs().length != 1) {
            return ;
        }
        //获取被点击的 View
        View view = (View) joinPoint.getArgs()[0];
        if (view == null) {
            return;
        }
        if (view.getId() != View.NO_ID) {
            String idString = view.getContext().getResources().getResourceEntryName(view.getId());
            Log.d(TAG,"ViewOnClickListenerAspectj beforePcOnClickListener 方法，被点击的View id:"+idString);
        }
    }

    @After("pointcutOnClickListener()")
    public void afterPcOnClickListener(final JoinPoint joinPoint) throws Throwable {
        Log.d(TAG,"ViewOnClickListenerAspectj afterPcOnClickListener 方法，在onClick之 后 被调用");
        //获取被点击的 View
        View view = (View) joinPoint.getArgs()[0];
        if (view == null) {
            return;
        }
        if (view.getId() != View.NO_ID) {
            String idString = view.getContext().getResources().getResourceEntryName(view.getId());
            Log.d(TAG,"ViewOnClickListenerAspectj afterPcOnClickListener 方法，被点击的View id:"+idString);
        }
    }

}
