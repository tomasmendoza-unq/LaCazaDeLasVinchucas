package unq.integrador.Enums;

public enum TipoOpinion {
    VINCHUCA_INFESTANS(){
      @Override
      public String imprimirTipo(){
        return "Vinchuca Infestans";
      }
    },
    VINCHUCA_SORDIDA(){
      @Override
      public String imprimirTipo(){
        return "Vinchuca Sordida";
      }
    },
    VINCHUCA_GUASAYANA(){
      @Override
      public String imprimirTipo(){
        return "Vinchuca Guasayana";
      }
    },
    CHINCHA_FOLIADA(){
      @Override
      public String imprimirTipo(){
        return "Chincha Foliada";
      }
    },
    PHTIA_CHINCHE(){
      @Override
      public String imprimirTipo(){
        return "Phtia Chinche";
      }
    },
    NINGUNA(){
      @Override
      public String imprimirTipo(){
        return "Ninguna";
      }
    },
    IMAGEN_POCO_CLARA(){
      @Override
      public String imprimirTipo(){
        return "Imagen poco clara";
      }
    };
    public abstract String imprimirTipo();
}
