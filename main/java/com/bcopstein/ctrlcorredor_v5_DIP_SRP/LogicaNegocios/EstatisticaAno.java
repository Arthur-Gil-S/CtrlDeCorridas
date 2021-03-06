package main.java.com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class EstatisticaAno implements ICalculoEstatistica{

    private IEventoRepository eventoRep;
    
    Date d = new Date();

    int anoCorrente = d.getYear() + 1900;

    @Service
    public EstatisticaAno(IEventoRepository eventoRepository){
        this.eventoRep = eventoRep;
    }

    @Override
    public EstatisticasDTO CalculoAnoCorrente(int distancia){
        // Seleciona os eventos da distancia informada
        List<Evento> eventos = 
            eventoRep.todos()
                .stream()
                .filter(e->e.getDistancia() == distancia)
                .filter(e->e.getAno() == anoCorrente)
                .collect(Collectors.toList());
        // Obtém um stream com os valores ordenados
        List<Double> valores = eventos
            .stream()
            .map(e-> e.getHoras()*60*60 + e.getMinutos()*60.0 + e.getSegundos())
            .sorted()
            .collect(Collectors.toList());
        // Calcula a média
        double media = valores
            .stream()
            .mapToDouble(v->v)
            .average()
            .orElse(Double.NaN);
        // Calcula mediana
        Double mediana = Double.NaN;
        if (valores.size() > 0){
            mediana =
                ((valores.size() % 2 == 0) ?
                (valores.get(valores.size()/2 - 1))+(valores.get(valores.size()/2))/2.0 :
                (valores.get(valores.size()/2)));
        }
        // Calcula o desvio padrao
        double varianca;
        double desvioPadrao = Double.NaN;
        if (mediana != Double.NaN){
            varianca = valores
                .stream()
                .mapToDouble(v -> v - media)
                .map(v -> v*v)
                .average().getAsDouble();
            desvioPadrao = Math.sqrt(varianca);
        }
        
        return new EstatisticasDTO(media, mediana, desvioPadrao);
    }
}
