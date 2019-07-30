package com.gdut.bytedance_interview;

/**
 * @author Li Xuyang
 * date  2019/7/30 15:40
 * 单例-懒汉式,用的比较多的 同步锁DCL
 * 这个之前写了，没问题，但是，问我怎么调用
 * 我写了 Singleton instance = Singleton.getInstance();
 *  好像也没什么问题  接着，快速排序
 */
public class Singleton {
    // 加 volatile关键字， 懒汉式变种，属于懒汉式中最好的写法，保证了：延迟加载和线程

    /* volatile关键字用处，
        1 防止重排序
        2 线程可见性 - 某一个线程改了公用对象（变量），短时间内另一个线程可能是不可见的，因为每一个线程都有自己的缓存区（线程工作区）
     */
    private static volatile Singleton mInstance;

    private Singleton() {

    }

    //加锁 synchronized
    //既保证线程的安全又是效率比较高的
    public static Singleton getInstance() {

        if (mInstance == null) {
            synchronized (Singleton.class) {
                //if 一定要加
                if (mInstance == null) {
                    mInstance = new Singleton();
                }
            }
        }
        return mInstance;
    }
}
