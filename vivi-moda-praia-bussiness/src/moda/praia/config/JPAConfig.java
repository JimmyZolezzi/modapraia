package moda.praia.config;



import moda.praia.modulo.avaliacao.repositorios.AvaliacaoRepository;
import moda.praia.modulo.clientes.repositorios.ClienteRepository;
import moda.praia.modulo.clientes.repositorios.HibernateTokenRepositoryImpl;
import moda.praia.modulo.estoque.repositorios.HistoricoMovimentacaEstoqueRepository;
import moda.praia.modulo.estoque.repositorios.ItemProdutoEstoqueRepository;
import moda.praia.modulo.pedido.repositorios.PedidoRepository;
import moda.praia.modulo.produtos.repositorios.ItemProdutoRepository;
import moda.praia.modulo.produtos.repositorios.ProdutoRepository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"moda.praia.modulo","moda.praia.modulo.produtos","moda.praia.modulo.produtos.dao"})
@EnableJpaRepositories(basePackageClasses={ProdutoRepository.class,ItemProdutoRepository.class,ItemProdutoEstoqueRepository.class, HistoricoMovimentacaEstoqueRepository.class, ClienteRepository.class, PedidoRepository.class, HibernateTokenRepositoryImpl.class, AvaliacaoRepository.class})
@ImportResource("classpath:config-jpa-spring.xml")
//@EnableSpringDataWebSupport
public class JPAConfig {
	
	

	//@Autowired
	//private ProdutoRepository produtoRepository;
	/*
	 * 
	 * 

	 @Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {

		 PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		    resolver.setPageParameterName("1");
		    resolver.setSizeParameterName("10");
		    resolver.setOneIndexedParameters(true);
		    argumentResolvers.add(resolver);
		    super.addArgumentResolvers(argumentResolvers);

	 }
	@Bean(name="entityManagerFactory")
	@Autowired
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(DataSource datasource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setDataSource(datasource);
		lcemfb.setPersistenceUnitName("viviModaPraiaPersistenceUnit");
		LoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
		lcemfb.setLoadTimeWeaver(loadTimeWeaver);
		lcemfb.setJpaVendorAdapter(jpaVendorAdapter);
		return lcemfb;
	}

	@Bean(name="datasource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/sunvibesbd?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		// I'm using MySQL5InnoDBDialect to make my tables support foreign keys
		return adapter;
	}

	
	@Bean(name="transactionManager")
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {	
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(entityManagerFactory);
		tm.setTransactionSynchronization(AbstractPlatformTransactionManager.SYNCHRONIZATION_ALWAYS);
		tm.setDataSource(getDataSource());
		return new JpaTransactionManager(entityManagerFactory);
	}
*/
}
