package work;

public class researchMyBatis {
	
	
	//1. JDBC:
	//ORM:object relation map对象关系映射，mybatis和hibernate都是ORM框架，建立数据库和对象之间映射
	//JDBC指的就是JAVA DATABASE CONNECT java数据库连接，主要步骤是：注册数据库，建立连接，执行sql，处理结果，关闭数据库
	//所谓的MyBatis就是JDBC的替代，封装好的处理数据库的框架
	
	//2. MyBatis的应用详解：
	//(1) 需要添加依赖，mybatis,mysql-connector-java
	//(2) 需要创建pojo对象，只有通过操作pojo对象才能操作数据库中数据，比如：user对象(@Data)对应数据库中的user表
	//(3) 添加全局配置文件xml，里面有个<mapper> 里面的resource就是映射文件，我们的SQL语句都写在映射文件(userMapper.xml)里面,注意我们写的映射文件必须关联写入配置文件中
	//(4) 映射文件里面会写上namespace表示本次的SQL运行的路径空间，namespace+id就可以运行指定的SQL语句
	
	//3. 直接使用的实现细节：
	//(1) #{}里面放的是我们执行SQL时候传过来的参数,比如：#{id},#{userName},#{userAge}
	//(2) 具体的哪一条SQL语句执行，用的是“id”,每个语句都有一个id，这样执行时候用namespace.id就可以确定运行的SQL是哪一句
	//(3) resultType返回值类型；parameterType传入参数的类型
	
	//4. 基于Mapper接口的使用详解：
	//(1) Dao里面创建userMapper接口，有三个相应的规定要求：*****接口中声明的方法必须和映射文件userMapper.xml中的id一致； 映射文件中namespace必须和接口同路径(放同一个Dao里面)
	//*****接口的名称必须和映射文件相同，都是userMapper,只不过一个是xml映射文件，一个是接口
	
	//5. 全局配置文件详解：
	//(1) dtd是mybatis-config的配置约束的核心，里面规定了configuration( properties?,settings?,...)？表示0，1个，“，”表示必须按顺序来
	//(2) properties:引入外部属性，便于使用。*****可以通过resource来引入.properties文件，也可以直接定义property的name和value,之后就可以用${name}替换value
	//(3) settings：有关mybatis的系统设置的，缓存，日志，懒加载等等
	//(4) typeAliases:(不区分大小写)给具体的类型取别名   <typeAliases type="原来的" aliases="别名">；也可以直接指定一整个包(意味着使用时候可以省略该包名)：<package name="...">
	//mybatis预定义好的别名，在映射文件中int---JAVA中的Integer,_int---JAVA中的int
	//(5) typeHanlders:类型处理器，用于数据库数据类型和JAVA数据类型之间的适配，适配器Adapter;如果通过自带的处理器没办法适配，那就需要自定义typeHandler
	//例如：ListVarcharTypeHanlder extends BaseTypeHanlder然后重写对应的几个方法(两种数据类型的转化操作)，写好了类然后放入全局配置文件即可<typeHanlder handler=" ">
	//(6) objectFactory:ORM框架中，每条表记录对应着一个自定义的Object对象，而objectFactory就是完成这个创建过程的,也可以自定义这个创建过程
	//(7) environments:管理数据库环境，一个environment就是一个数据源，代表一个数据库，如果是多源，那就是多个environment;transactionManager事务管理，分为JDBC,MANAGED(Spring容器)；dataSource数据源
	//(8) mappers:映射文件位置，resource相对于类的路径，url完整路径，class定位到接口(再由接口自动链接到映射文件)，package直接链接到包中的所有接口
	
	//6. 映射文件详解：
	//(1) cache:缓存：减少数据库的访问频率；一级缓存(本地缓存)中有数据，就不用访问数据库了，直接拿出来。一级缓存默认打开，随着session关闭，缓存清空。
	//二级缓存：解决一级缓存范围不足的问题，可以跨sqlSession,是namespace级别的；打开二级缓存：在映射文件中加入<cache/>;select标签中可以通过useCache来设置是否使用缓存
	//(2) cache-ref:引用其他映射文件的缓存配置，让cache-ref标签中namespace为另一个映射文件
	//(3) select:statementType设置为statement表示不能用占位符，#{id}不给用，statement用的是${id}来处理
	//(4) DML改删插：执行插入时候，可以生成主键，useGeneratedKey,指定key即可自动生成
	//(5) *****sql:可重用代码，将重复代码用sql标签包起来，需要使用时候，用<include>标签来插入代码，<sql id="1"></sql>   <include refid="1"></include>
	//(6) *****resultMap:对结果集做个映射，里面result标签可以指定column和property的对应关系，例如：<resultMap id="1"><result column="user" property="person">
	
	//7. 动态SQL语句详解：
	//去掉标签，组成完整规范的SQL语句
	//(1) if：条件，test里面放条件，内部为执行，where 1=1 <if test="age>0"> and age=#{age}  </if>
	//*****所有的标签去掉后，需要形成一个完整的sql语句，所以这里需要加上and表示“where 1=1 and age=#{age} and ...”
	//所以更新时候有个问题，就是“，”的位置问题，第一个if不满足，后面都有个“，”语法出错，这就是(4)set使用的原因
	//(2) where:和if连在一起用,如果不用where标签，那么必须写成where 1=1 然后才是if标签;用where标签，就不用1=1了，直接用
	//(3) choose:从多个条件中选择一个使用，类似于switch,用法：<choose>  <when test="条件1">and   </when> <when test="条件2">and   </when>  </choose>
	//(4) set:动态更新，按需更新，更新时候，<set> 更新操作 </set>
	//(5) trim:替代where,set标签，设置prefix前缀，且有前缀and覆盖掉的功能prefixOverride
	//(6) foreach:集合遍历，collection指定集合名字，item表示遍历的单个元素，open,close表示左右添加，如：(1,2,3,4,5)---<foreach collection="id" item="ids" open="(" close=")">#{item}<foreach>
	//注意动态SQL只是为了添加到普通SQL语句里面去，所以去掉标签后，一定要形成规范的SQL，所以上面的()必须加
	//(7) bind:简单的声明变量并绑定值
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
