package main.java.com.bcopstein.ctrlcorredor_v5_DIP_SRP.Interface;

import java.util.List;

import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.Corredor;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.EstatisticasDTO;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.Evento;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.PerformanceDTO;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.ServicoCorredor;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.ServicoEstatistica;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.ServicoEvento;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.EstatisticaOriginal;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.EstatisticaAnoCorrente;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.EstatisticaDesconsidera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.annotation.Configuration;
import org.springframework.annotation.Bean;

@Configuration
public class ConfigCalculoEstatistica {
    private IEventoRepository eventoRep;

    @Autowired
    public ConfigCalculoEstatistica(IEventoRepository eventoRep) {
        this.eventoRep = eventoRep;
    }

    @Bean
    @ConditionalOnProperty(name = "calculo.estatistica", havingValue = "original", matchIfMissing = true)
    public ICalculoEstatistica opcaoRegraClassica() {
        return new CalculoOriginal();
    }

    @Bean
    @ConditionalOnProperty(name = "calculo.estatistica", havingValue = "desconsidera")
    public ICalculoEstatistica opcaoDesconsidera() {
        return new CalculoDesconsidera();
    }

    @Bean
    @ConditionalOnProperty(name = "calculo.estatistica", havingValue = "anoCorrente")
    public ICalculoEstatistica opcaoAnoCorrente() {
        return new CalculoAnoCorrente();
    }
}