```
//base url
https://api.douban.com/v2
//apikey
apikey = 0df993c66c0c636e29ecbb5344252a4a
apikey = 0b2bdeda43b5688921839c8ecb20399b
```


## 电影
列表 type=in_theaters/coming_soon/top250
/movie/${type}?start=0&count=20

详情 movieId
/movie/subject/${movieId}




### 正在热映：
https://api.douban.com/v2/movie/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a&start=0&count=10

### 即将上映：
https://api.douban.com/v2/movie/coming_soon?apikey=0df993c66c0c636e29ecbb5344252a4a&start=0&count=10

### top250：
https://api.douban.com/v2/movie/top250?apikey=0df993c66c0c636e29ecbb5344252a4a&start=0&count=10

### 口碑榜：
https://api.douban.com/v2/movie/weekly?apikey=0df993c66c0c636e29ecbb5344252a4a

### 北美票房榜
https://api.douban.com/v2/movie/us_box?apikey=0df993c66c0c636e29ecbb5344252a4a

### 新片榜
https://api.douban.com/v2/movie/new_movies?apikey=0df993c66c0c636e29ecbb5344252a4a



### 电影详情：
https://api.douban.com/v2/movie/subject/1292052?apikey=0df993c66c0c636e29ecbb5344252a4a


### 获取电影剧照
https://api.douban.com/v2/movie/subject/1292052/photos?count=100&apikey=0df993c66c0c636e29ecbb5344252a4a



### 获取电影条目短评论
https://api.douban.com/v2/movie/subject/1292052/comments?count=100&apikey=0df993c66c0c636e29ecbb5344252a4a

### 获取电影条目长评论
https://api.douban.com/v2/movie/subject/1292052/reviews?count=100&apikey=0df993c66c0c636e29ecbb5344252a4a







### 豆瓣电影搜索不能用

H5方式搜索
https://search.douban.com/movie/subject_search?search_text=新少林寺

https://api.douban.com/v2/movie/search?apikey=0df993c66c0c636e29ecbb5344252a4a&q=张艺谋&start=0&count=20

https://api.douban.com/v2/movie/search?apikey=0df993c66c0c636e29ecbb5344252a4a&tag=科幻&start=0&count=20


https://api.douban.com/v2/movie/search?q=张艺谋&apikey=0df993c66c0c636e29ecbb5344252a4a



## 演员
演员 actorId
详情
/movie/celebrity/${actorId}


### 获取影人条目信息
0df993c66c0c636e29ecbb5344252a4a
0b2bdeda43b5688921839c8ecb20399b
https://api.douban.com/v2/movie/celebrity/3543690?apikey=0b2bdeda43b5688921839c8ecb20399b

### 获取影人作品
https://api.douban.com/v2/movie/celebrity/1044707/works?apikey=0b2bdeda43b5688921839c8ecb20399b

https://api.douban.com/v2/movie/celebrity/1044707/works?start=1&count=100&apikey=0b2bdeda43b5688921839c8ecb20399b



### 获取影人剧照
https://api.douban.com/v2/movie/celebrity/:id/photos?start=xxx&count=xxx&apikey=xxxx

Example:
GET   https://api.douban.com/v2/movie/celebrity/1044707/photos?apikey=0b2bdeda43b5688921839c8ecb20399b



## 图书
### 图书标签
tags: [{"title":"文学","tag":["小说","外国文学","文学","随笔","中国文学","经典","日本文学","散文","村上春树","诗歌","童话","王小波","杂文","古典文学","儿童文学","名著","张爱玲","余华","当代文学","钱钟书","外国名著","鲁迅","诗词","茨威格","米兰·昆德拉","杜拉斯","港台"]},{"title":"流行 ","tag":["漫画","绘本","推理","青春","东野圭吾","科幻","言情","悬疑","武侠","奇幻","韩寒","日本漫画","耽美","亦舒","推理小说","三毛","网络小说","安妮宝贝","郭敬明","穿越","金庸","轻小说","阿加莎·克里斯蒂","几米","科幻小说","魔幻","青春文学","张小娴","幾米","J.K.罗琳","高木直子","古龙","沧月","落落","张悦然","校园"]},{"title":"文化","tag":["历史","心理学","哲学","传记","文化","社会学","艺术","设计","社会","政治","建筑","宗教","电影","数学","政治学","回忆录","中国历史","思想","国学","音乐","人文","人物传记","绘画","戏剧","艺术史","佛教","军事","西方哲学","二战","近代史","考古","自由主义","美术"]},{"title":"生活","tag":["爱情","旅行","生活","成长","励志","心理","摄影","女性","职场","美食","教育","游记","灵修","健康","情感","手工","两性","养生","人际关系","家居","自助游"]},{"title":"经管","tag":["经济学","管理","经济","商业","金融","投资","营销","创业","理财","广告","股票","企业史","策划"]},{"title":"科技","tag":["科普","互联网","编程","科学","交互设计","用户体验","算法","web","科技","UE","通信","交互","UCD","神经网络","程序"]}]

### 列表
/book/search?tag=${tag}&start=0&count=20&fields=id,title,images

### 详情
/book/${bookId}


### 搜索图书
https://api.douban.com/v2/book/search?apikey=0df993c66c0c636e29ecbb5344252a4a&tag=程序&start=0&count=20
&fields=title

### 图书详情
https://api.douban.com/v2/book/26264642?apikey=0df993c66c0c636e29ecbb5344252a4a

### 据ISBN码获取图书信息
https://api.douban.com/v2/book/isbn/:isbn
>Example:
 https://api.douban.com/v2/book/isbn/9787115350541?apikey=0df993c66c0c636e29ecbb5344252a4a


## 音乐
### 搜索音乐
https://api.douban.com/v2/music/search

Example:
GET    https://api.douban.com/v2/music/search?q=少年

### 获取音乐信息
https://api.douban.com/v2/music/:id

Example:
GET  https://api.douban.com/v2/music/2337701




--------------------------------------------------------------------------------






## 豆瓣电影中的一些接口

获取标签
https://movie.douban.com/j/search_tags
获取电影
https://movie.douban.com/j/search_subjects?tag=热门
获取电影条目详情
https://movie.douban.com/j/subject_abstract?subject_id=4917832



https://movie.douban.com/j/subject_abstract?subject_id=4917832



