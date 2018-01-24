

##遗留问题

###1. 启动页面
###2. 图标
###3.


##RxJava



###RxJave的概述

####  概念：其实 就是了解观察者模式 和异步两个概念





####  骨架：
        //创建被观察者，是事件传递的起点
        Observable.just("On","Off","On","On")
                //这就是在传递过程中对事件进行过滤操作
                 .filter(new Func1<String, Boolean>() {
                            @Override
                            public Boolean call(String s) {
                                return s！=null;
                            }
                        })
                //实现订阅
                .subscribe(
                        //创建观察者，作为事件传递的终点处理事件
                          new Subscriber<String>() {
                                @Override
                                public void onCompleted() {
                                    Log.d("DDDDDD","结束观察...\n");
                                }

                                @Override
                                public void onError(Throwable e) {
                                    //出现错误会调用这个方法
                                }
                                @Override
                                public void onNext(String s) {
                                    //处理事件
                                    Log.d("DDDDD","handle this---"+s)
                                }
                );

#### 操作符:

    XXX

#### 线程调度 :
    XXX



####描述：

    它就是在观察者模式的骨架下，通过丰富的操作符和便捷的异步操作来完成对于复杂业务的处理。



###RxJave2.0的概述



    观察者模式

    首先声明，RxJava以观察者模式为骨架，在2.0中依然如此。

    不过此次更新中，出现了两种观察者模式：

    Observable(被观察者)/Observer（观察者）


    Flowable(被观察者)/Subscriber(观察者)

    在RxJave1.0中



    RxJava是一个观察者模式的架构，当这个架构中被观察者(Observable)和观察者(Subscriber)处在不同的线程环境中时，由于者各自的工作量不一样，导致它们产生事件和处理事件的速度不一样，这就会出现两种情况：

    被观察者产生事件慢一些，观察者处理事件很快。那么观察者就会等着被观察者发送事件，（好比观察者在等米下锅，程序等待，这没有问题）。
    被观察者产生事件的速度很快，而观察者处理很慢。那就出问题了，如果不作处理的话，事件会堆积起来，最终挤爆你的内存，导致程序崩溃。（好比被观察者生产的大米没人吃，堆积最后就会烂掉）。



    //被观察者在主线程中，每1ms发送一个事件
    Observable.interval(1, TimeUnit.MILLISECONDS)
                    //.subscribeOn(Schedulers.newThread())
                    //将观察者的工作放在新线程环境中
                    .observeOn(Schedulers.newThread())
                    //观察者处理每1000ms才处理一个事件
                    .subscribe(new Action1<Long>() {
                          @Override
                          public void call(Long aLong) {
                              try {
                                  Thread.sleep(1000);
                              } catch (InterruptedException e) {
                                  e.printStackTrace();
                              }
                              Log.w("TAG","---->"+aLong);
                          }
                      });




    在RxJave 2.0中

        Observable(被观察者)-------------Observer（观察者） 不支持背压

        Flowable ------------------Subscriber  支持背压


        关于背压这个概念，：背压是指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略，在1.0中，关于背压最大的遗憾，
        就是集中在Observable这个类中，导致有的Observable支持背压，有的不支持。为了解决这种缺憾，新版本把支持背压和不支持背压的Observable区分开来。



        在RxJava的观察者模型中，被观察者是主动的推送数据给观察者，观察者是被动接收的。而响应式拉取则反过来，观察者主动从被观察者那里去拉取数据，而被观察者变成被动的等待通知再发送数据。



        //被观察者将产生100000个事件
        Observable observable=Observable.range(1,100000);
        class MySubscriber extends Subscriber<T> {
            @Override
            public void onStart() {
            //一定要在onStart中通知被观察者先发送一个事件
              request(1);
            }

            @Override
            public void onCompleted() {
                ...
            }

            @Override
            public void onError(Throwable e) {
                ...
            }

            @Override
            public void onNext(T n) {
                ...
                ...
                //处理完毕之后，在通知被观察者发送下一个事件
                request(1);
            }
        }

        observable.observeOn(Schedulers.newThread())
                    .subscribe(MySubscriber);
        在代码中，传递事件开始前的onstart()中，



        下面我们总结一下：

        背压是一种策略，具体措施是下游观察者通知上游的被观察者发送事件
        背压策略很好的解决了异步环境下被观察者和观察者速度不一致的问题
        在RxJava1.X中，同样是Observable，有的不支持背压策略，导致某些情况下，显得特别麻烦，出了问题也很难排查，使得RxJava的学习曲线变得十份陡峭。
        这篇文章并不是为了让你学习在RxJava1.0中使用背压（如果你之前不了解背压的话），因为在1.0中，背压的设计并不十分完美。而是希望你对背压有一个全面清晰的认识，对于它在RxJava1.0中的设计缺陷有所了解即可。因为这篇文章本身是为了2.0做一个铺垫，后续的文章中我会继续谈到背压和使用背压的正确姿势。



