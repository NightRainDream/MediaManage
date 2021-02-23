# MediaManage
Android媒体选择库

[![](https://jitpack.io/v/NightRainDream/MediaManage.svg)](https://jitpack.io/#NightRainDream/MediaManage)

# 导入
```
allprojects {
    repositories {
        ......
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```
```
implementation 'com.github.NightRainDream:MediaManage:v1.0.0'
```

# 使用
### 初始化
在Application的onCreate中初始化Library.(`GlideRealSubject`类请查看Demo)
```
MediaSelectHelp.init(GlideRealSubject())
```
### 选择图片
```
MediaSelectHelp.BuildSelectPicture()
            .launch(this, object : MediaSelectCallback {
                override fun onSelectSuccess(entity: MediaEntity) {
                   
                }

                override fun onSelectError(error: String) {
                   
                }
            })
```
### 选择视频
```
MediaSelectHelp.BuildSelectVideo()
            .launch(this, object : MediaSelectCallback {
                override fun onSelectSuccess(entity: MediaEntity) {
                   
                }

                override fun onSelectError(error: String) {
                   
                }
            })
```
### 选择音频
```
MediaSelectHelp.BuildSelectAudio()
            .launch(this, object : MediaSelectCallback {
                override fun onSelectSuccess(entity: MediaEntity) {
                   
                }

                override fun onSelectError(error: String) {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
                }
            })
```

