package com.project.jvm.test;

/**
 * 当内部类是静态内部类的时候，创建其实例时就把其静态属性创建在方法区中，从而静态修饰的对象已经作为了GC root的根，
 * 其可以通过引用链找到的对象就不会被GC回收，从而造成内存泄漏的问题。
 */
public class MemoryLeakTest {

    abstract static class Component  {
        final void create() {
            onCreate();
        }

        final void destroy() {
            onDestroy();
        }
        abstract void onCreate();
        abstract void onDestroy();
    }

    static class MyComponent extends Component {

        static OnClickListener onClickListener;

        MyWindow myWindow;

        @Override
        void onCreate() {
            onClickListener = new OnClickListener() {
                @Override
                public void OnClick(Object obj) {
                    System.out.println("对象"+obj+"被点击");
                }
            };
            myWindow = new MyWindow();
            myWindow.setClickListener(onClickListener);
        }
        @Override
        void onDestroy() {
            myWindow.removeClickListener();
        }
    }

    static class MyWindow {
        OnClickListener clickListener;

        // 设置当前控件的单击事件监听器
        void setClickListener(OnClickListener clickListener) {
            this.clickListener = clickListener;
        }

        // 移除当前控件的单击事件监听器
        void removeClickListener() {
            this.clickListener = null;
        }
    }

    interface OnClickListener{
        void OnClick(Object obj);
    }

    public static void main(String[] args) {
        MyComponent myComponent = new MyComponent();
        myComponent.create();
        myComponent.destroy();
        // myComponent 引用置为 null，排除它的干扰
        myComponent = null;
        // 调用 JVM 的垃圾回收动作，回收无用对象
        System.gc();
        System.out.println("---");
    }
}
