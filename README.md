UserGuider
==========

    UserGuider is a tool to introduce user to your newly developed product on you mobile application.


Overview
--------

    UserGuider provides four kinds of implementation for your introduction. 
    1. DGuider / VGuider provides a single guide view in the window each time.
    2. DMultiGuider / VMultiGuider can provides multi guide view each time.
    Prefix D implies Dialog implementation, and V for DecorView implementation.
    So choose the one you need or like.

    There are several properties you can provide to customize the introduction.
    1. GuideView / GuideViewId: A view or resource id holding the introduction information.
    1. Anchor: The area you want to put you guide tips around. if not provided, 
       guide tips will be located in the screen center.
    2. Position: The position to locate the guide view relative to anchor. 25 candidates available.
    3. BackgroundColor / DisableAnim / Delay: Properties to customize the window.

```java
            ______________TOP_OUT______________
            |             TOP_IN              |
            |                                 |
            |                                 |
    LEFT_OUT|LEFT_IN      CENTER      RIGHT_IN|RIGHT_OUT
            |                                 |
            |                                 |
            |_____________BOTTOM_IN___________|
                          BOTTOM_OUT
```


Example
-------

```java
        new DGuider.Builder()
                .setAnchor(anchor)
                .setBackground(0x4Cf00000)
                .setGuideViewId(R.layout.guide_layout)
                .setPosition(DGuider.LEFT_IN | DGuider.BOTTOM_OUT)
                .disableAnim(true)
                .build(Activity.this)
                .show();
```

```java
        DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchor1).setGuideViewId(R.layout.guide_layout)
                        .setBackground(0x4C00f000)
                        .setPosition(DMultiGuider.TOP_OUT| DMultiGuider.RIGHT_IN).buildItem();
                builder.setAnchor(anchor2).setGuideViewId(R.layout.guide_layout)
                        .setPosition(DMultiGuider.CENTER| DMultiGuider.CENTER).buildItem();
                builder.setAnchor(anchor3).setGuideViewId(R.layout.guide_layout)
                        .setPosition(DMultiGuider.RIGHT_IN| DMultiGuider.BOTTOM_IN).buildItem();
                builder.build(Activity.this).show();
```

License
=======

    Copyright 2016 CodeSky, Inc.

