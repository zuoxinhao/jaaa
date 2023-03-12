package work;

public class researchMyBatis {
	
	
	//1. JDBC:
	//ORM:object relation map�����ϵӳ�䣬mybatis��hibernate����ORM��ܣ��������ݿ�Ͷ���֮��ӳ��
	//JDBCָ�ľ���JAVA DATABASE CONNECT java���ݿ����ӣ���Ҫ�����ǣ�ע�����ݿ⣬�������ӣ�ִ��sql�����������ر����ݿ�
	//��ν��MyBatis����JDBC���������װ�õĴ������ݿ�Ŀ��
	
	//2. MyBatis��Ӧ����⣺
	//(1) ��Ҫ���������mybatis,mysql-connector-java
	//(2) ��Ҫ����pojo����ֻ��ͨ������pojo������ܲ������ݿ������ݣ����磺user����(@Data)��Ӧ���ݿ��е�user��
	//(3) ���ȫ�������ļ�xml�������и�<mapper> �����resource����ӳ���ļ������ǵ�SQL��䶼д��ӳ���ļ�(userMapper.xml)����,ע������д��ӳ���ļ��������д�������ļ���
	//(4) ӳ���ļ������д��namespace��ʾ���ε�SQL���е�·���ռ䣬namespace+id�Ϳ�������ָ����SQL���
	
	//3. ֱ��ʹ�õ�ʵ��ϸ�ڣ�
	//(1) #{}����ŵ�������ִ��SQLʱ�򴫹����Ĳ���,���磺#{id},#{userName},#{userAge}
	//(2) �������һ��SQL���ִ�У��õ��ǡ�id��,ÿ����䶼��һ��id������ִ��ʱ����namespace.id�Ϳ���ȷ�����е�SQL����һ��
	//(3) resultType����ֵ���ͣ�parameterType�������������
	
	//4. ����Mapper�ӿڵ�ʹ����⣺
	//(1) Dao���洴��userMapper�ӿڣ���������Ӧ�Ĺ涨Ҫ��*****�ӿ��������ķ��������ӳ���ļ�userMapper.xml�е�idһ�£� ӳ���ļ���namespace����ͽӿ�ͬ·��(��ͬһ��Dao����)
	//*****�ӿڵ����Ʊ����ӳ���ļ���ͬ������userMapper,ֻ����һ����xmlӳ���ļ���һ���ǽӿ�
	
	//5. ȫ�������ļ���⣺
	//(1) dtd��mybatis-config������Լ���ĺ��ģ�����涨��configuration( properties?,settings?,...)����ʾ0��1������������ʾ���밴˳����
	//(2) properties:�����ⲿ���ԣ�����ʹ�á�*****����ͨ��resource������.properties�ļ���Ҳ����ֱ�Ӷ���property��name��value,֮��Ϳ�����${name}�滻value
	//(3) settings���й�mybatis��ϵͳ���õģ����棬��־�������صȵ�
	//(4) typeAliases:(�����ִ�Сд)�����������ȡ����   <typeAliases type="ԭ����" aliases="����">��Ҳ����ֱ��ָ��һ������(��ζ��ʹ��ʱ�����ʡ�Ըð���)��<package name="...">
	//mybatisԤ����õı�������ӳ���ļ���int---JAVA�е�Integer,_int---JAVA�е�int
	//(5) typeHanlders:���ʹ��������������ݿ��������ͺ�JAVA��������֮������䣬������Adapter;���ͨ���Դ��Ĵ�����û�취���䣬�Ǿ���Ҫ�Զ���typeHandler
	//���磺ListVarcharTypeHanlder extends BaseTypeHanlderȻ����д��Ӧ�ļ�������(�����������͵�ת������)��д������Ȼ�����ȫ�������ļ�����<typeHanlder handler=" ">
	//(6) objectFactory:ORM����У�ÿ�����¼��Ӧ��һ���Զ����Object���󣬶�objectFactory�����������������̵�,Ҳ�����Զ��������������
	//(7) environments:�������ݿ⻷����һ��environment����һ������Դ������һ�����ݿ⣬����Ƕ�Դ���Ǿ��Ƕ��environment;transactionManager���������ΪJDBC,MANAGED(Spring����)��dataSource����Դ
	//(8) mappers:ӳ���ļ�λ�ã�resource��������·����url����·����class��λ���ӿ�(���ɽӿ��Զ����ӵ�ӳ���ļ�)��packageֱ�����ӵ����е����нӿ�
	
	//6. ӳ���ļ���⣺
	//(1) cache:���棺�������ݿ�ķ���Ƶ�ʣ�һ������(���ػ���)�������ݣ��Ͳ��÷������ݿ��ˣ�ֱ���ó�����һ������Ĭ�ϴ򿪣�����session�رգ�������ա�
	//�������棺���һ�����淶Χ��������⣬���Կ�sqlSession,��namespace����ģ��򿪶������棺��ӳ���ļ��м���<cache/>;select��ǩ�п���ͨ��useCache�������Ƿ�ʹ�û���
	//(2) cache-ref:��������ӳ���ļ��Ļ������ã���cache-ref��ǩ��namespaceΪ��һ��ӳ���ļ�
	//(3) select:statementType����Ϊstatement��ʾ������ռλ����#{id}�����ã�statement�õ���${id}������
	//(4) DML��ɾ�壺ִ�в���ʱ�򣬿�������������useGeneratedKey,ָ��key�����Զ�����
	//(5) *****sql:�����ô��룬���ظ�������sql��ǩ����������Ҫʹ��ʱ����<include>��ǩ��������룬<sql id="1"></sql>   <include refid="1"></include>
	//(6) *****resultMap:�Խ��������ӳ�䣬����result��ǩ����ָ��column��property�Ķ�Ӧ��ϵ�����磺<resultMap id="1"><result column="user" property="person">
	
	//7. ��̬SQL�����⣺
	//ȥ����ǩ����������淶��SQL���
	//(1) if��������test������������ڲ�Ϊִ�У�where 1=1 <if test="age>0"> and age=#{age}  </if>
	//*****���еı�ǩȥ������Ҫ�γ�һ��������sql��䣬����������Ҫ����and��ʾ��where 1=1 and age=#{age} and ...��
	//���Ը���ʱ���и����⣬���ǡ�������λ�����⣬��һ��if�����㣬���涼�и��������﷨���������(4)setʹ�õ�ԭ��
	//(2) where:��if����һ����,�������where��ǩ����ô����д��where 1=1 Ȼ�����if��ǩ;��where��ǩ���Ͳ���1=1�ˣ�ֱ����
	//(3) choose:�Ӷ��������ѡ��һ��ʹ�ã�������switch,�÷���<choose>  <when test="����1">and   </when> <when test="����2">and   </when>  </choose>
	//(4) set:��̬���£�������£�����ʱ��<set> ���²��� </set>
	//(5) trim:���where,set��ǩ������prefixǰ׺������ǰ׺and���ǵ��Ĺ���prefixOverride
	//(6) foreach:���ϱ�����collectionָ���������֣�item��ʾ�����ĵ���Ԫ�أ�open,close��ʾ������ӣ��磺(1,2,3,4,5)---<foreach collection="id" item="ids" open="(" close=")">#{item}<foreach>
	//ע�⶯̬SQLֻ��Ϊ����ӵ���ͨSQL�������ȥ������ȥ����ǩ��һ��Ҫ�γɹ淶��SQL�����������()�����
	//(7) bind:�򵥵�������������ֵ
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
