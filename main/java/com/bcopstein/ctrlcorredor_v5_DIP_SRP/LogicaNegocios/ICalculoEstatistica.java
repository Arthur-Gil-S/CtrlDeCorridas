package main.java.com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios;

import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.EstatisticasDTO;

public interface ICalculoEstatistica {
    EstatisticasDTO CalculoOriginal(int distancia);
    EstatisticasDTO CalculoAnoCorrente(int distancia);
    EstatisticasDTO CalculoDesconsidera(int distancia);
}
