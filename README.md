# 介绍
## 自用项目，图片预览

### 图片
<div style="align: center">
       <img src="https://github.com/yiranchunqiu/PicPreview/blob/master/pic/%E5%9B%BE%E7%89%871.png" width="32%">
</div>


### 使用方法
### 添加

```
allprojects {
 		repositories {
 			maven { url 'https://jitpack.io' }
 		}
 	}
```

### 添加依赖

```
 	dependencies {
    	       implementation 'com.github.yiranchunqiu:PicPreview:1.0'
    	}
```

### 使用

```
            Bundle bundle = new Bundle();
            ArrayList strings = new ArrayList<String>();
            strings.clear();
            strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573619837194&di=da16f3ff5fab18ed311838591002ddf2&imgtype=0&src=http%3A%2F%2Fwww.forestry.gov.cn%2Fuploadfile%2Fmain%2F2013-6%2Fimage%2F2013-6-19-dbdb3e3f20b644ec959960e9d8308eda.jpg");
            strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573619837194&di=4642748edbc5baaf2f3dce2ae1bbb867&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F7Po3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2Fb3b7d0a20cf431addc902f154936acaf2edd985f.jpg");
            strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573619837194&di=aed9add6983c224ca2d116d2c33247d8&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fc9f62254a4a9651171d03c20872f8f745d6da05e1896e-XwHiqQ_fw658");
            strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573619837194&di=759c7d5d2794c308c52855e009bec8fa&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fcd470ea26df08149f5a773230be914775079bf9f5fbe5-ToScId_fw658");
            strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573619837194&di=3ae2fd3637b88f9c58287a3563b6a21a&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F-Po3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2F1e30e924b899a9011f26a6701f950a7b0208f561.jpg");
            strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573619837194&di=ac466cd40c86c17aefc0a63818b3f17e&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F88130711005c920bc121be7814e9e7ba5458c4494a83-cEMxmc_fw658");
            bundle.putInt("postion", 0);
            bundle.putStringArrayList("stringUrl", strings);
            Intent intent = new Intent();
            intent.setClass(this, PicturePreviewActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
```