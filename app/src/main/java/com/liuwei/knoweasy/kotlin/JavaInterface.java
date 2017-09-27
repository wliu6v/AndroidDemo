package com.liuwei.knoweasy.kotlin;

/**
 * Created by liuwei on 2017/9/22.
 */

public class JavaInterface {

	public interface INoParam {
		void foo();
	}

	public static void setNoParam(INoParam a) {
	}

	// ----
	public interface IParam {
		void foo(int a);
	}

	public static void setParam(IParam a) {
	}

	// ----
	public interface IReturn {
		int foo(int a);
	}


	public static void setHasReturn(IReturn a) {
	}

	// ----
	public interface IMultiParam {
		void foo(int a, String b);
	}

	public static void setMultiParam(IMultiParam a) {
	}

	// ----
	public interface IMultiMethods {
		void foo(int a);

		void bar(String b);
	}

	public static void setMultiMethods(IMultiMethods a) {
	}


}
