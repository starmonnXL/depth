package com.depth.cms.content.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 11:30 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class TypeCastException extends RuntimeException {
    private static final long serialVersionUID = -7441273323354947023L;
    Throwable nested = null;

    public TypeCastException() {
    }

    public TypeCastException(String msg) {
        super(msg);
    }

    public TypeCastException(String msg, Throwable nested) {
        super(msg);
        this.nested = nested;
    }

    public TypeCastException(Throwable nested) {
        this.nested = nested;
    }

    public String getMessage() {
        return this.nested != null?super.getMessage() + " (" + this.nested.getMessage() + ")":super.getMessage();
    }

    public String getNonNestedMessage() {
        return super.getMessage();
    }

    public Throwable getNested() {
        return (Throwable)(this.nested == null?this:this.nested);
    }

    public void printStackTrace() {
        super.printStackTrace();
        if(this.nested != null) {
            this.nested.printStackTrace();
        }

    }

    public void printStackTrace(PrintStream ps) {
        super.printStackTrace(ps);
        if(this.nested != null) {
            this.nested.printStackTrace(ps);
        }

    }

    public void printStackTrace(PrintWriter pw) {
        super.printStackTrace(pw);
        if(this.nested != null) {
            this.nested.printStackTrace(pw);
        }

    }
}

