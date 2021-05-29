package com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios;

import java.util.List;

public interface ICorredorRepository {
    List<Corredor> todos();
    boolean cadastra(Corredor corredor);
}
