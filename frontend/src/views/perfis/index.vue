<template>
  <v-container>
    <v-row class="d-flex justify-center align-center">
      <v-col cols="12" sm="6" md="3">
        <v-text-field label="CPF" v-model="cpf"></v-text-field>
      </v-col>
      <v-col cols="12" sm="6" md="6">
        <v-banner elevation="2">
          <span v-if="pessoa.nmFunc"
            >{{ pessoa.nrNip }} - {{ pessoa.nmFunc }}</span
          >
        </v-banner>
      </v-col>
    </v-row>
    <v-row class="d-flex justify-center align-center">
      <v-col cols="12" sm="6" md="3">
        <v-autocomplete
          v-model="moduloSelecionado"
          :items="modulos.map((e) => e.cdProj + e.idModu)"
          dense
          label="Modulo"
          :disabled="!pessoa.nmFunc"
          @change="handleChangeModulo"
        ></v-autocomplete>
      </v-col>
      <v-col cols="12" sm="6" md="3">
        <v-autocomplete
          v-model="oiSelecionado"
          :items="listaOi"
          item-label="oi"
          dense
          label="OI"
          @update:search-input="handleInputOi"
        ></v-autocomplete>
      </v-col>
    </v-row>
    <v-layout align-start justify-center>
      <v-flex xs6 class="elevation-1 pa-3 ma-2">
        <v-list v-if="perfis" dense>
          <v-subheader> PERFIL </v-subheader>
          <draggable
            v-model="perfis"
            :options="{ group: 'people' }"
            style="min-height: 10px"
          >
            <v-list-item v-for="(item, index) in perfis" :key="index">
              <v-list-item-content>
                <v-list-item-title
                  v-text="item.codigoNivelAcesso.idNivlAces"
                ></v-list-item-title>

                <!-- <v-list-item-subtitle
                  v-text="item.codigoNivelAcesso.idNivlAces"
                ></v-list-item-subtitle> -->
              </v-list-item-content>

              <v-list-item-action>
                <v-btn icon @click="associaPerfil(item)">
                  <v-icon color="grey lighten-1">mdi-arrow-right</v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
          </draggable>
        </v-list>
      </v-flex>
      <v-flex xs6 class="elevation-1 pa-3 ma-2">
        <v-list v-if="perfisAssociados" two-line>
          <v-subheader> PERFIL ASSOCIADO: {{ moduloSelecionado }}</v-subheader>
          <draggable
            v-model="perfisAssociados"
            :options="{ group: 'people' }"
            style="min-height: 10px"
          >
            <v-list-item v-for="(item, index) in perfisAssociados" :key="index">
              <v-list-item-content>
                <v-list-item-title
                  v-text="item.codigoNivelAcesso.idNivlAces"
                ></v-list-item-title>
                <v-list-item-subtitle>OI: {{ item.oi }}</v-list-item-subtitle>
              </v-list-item-content>

              <v-list-item-action>
                <v-btn icon @click="desassociaPerfil(item)">
                  <v-icon color="grey lighten-1">mdi-close</v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
          </draggable>
        </v-list>
      </v-flex>
    </v-layout>
    <snackbar
      :open="snack.open"
      :text="snack.text"
      :color="snack.color"
      @close="snack.open = false"
    />
  </v-container>
</template>

<script>
import draggable from "vuedraggable";
import axios from "axios";
import { debounce } from "debounce";
import snackbar from "../../components/snackbar";
export default {
  components: {
    draggable,
    snackbar,
  },
  data() {
    return {
      modulos: [],
      moduloSelecionado: "",
      cpf: "",
      pessoa: { nmFunc: "" },
      oiSelecionado: "",
      perfis: [],
      perfisAssociados: [],
      listaOi: [],
      listaCompletaOi: [],
      snack: { open: false, text: "", color: "" },
    };
  },
  watch: {
    cpf(val) {
      if (val.length < 11) {
        this.pessoa = { nmFunc: "" };
        return;
      }

      this.getPessoaPorCpf(val);
    },
  },
  computed: {
    findOi() {
      return this.listaCompletaOi.find((i) => i.oi === this.oiSelecionado);
    },
  },
  methods: {
    associaPerfil(item) {
      if (!this.oiSelecionado) return;

      const perfil = { ...item, oi: this.oiSelecionado };
      const existe = this.perfisAssociados.find(
        (f) =>
          f.oi.concat(f.key.cdNivlAces) ===
          perfil.oi.concat(perfil.key.cdNivlAces)
      );
      if (existe) {
        return;
      }

      let payload = {
        key: {
          nrCpf01: this.pessoa.nrCpfFunc01,
          nrCpf02: this.pessoa.nrCpfFunc02,
          nrCpf03: this.pessoa.nrCpfFunc03,
          idDvCpf: this.pessoa.idDvCpfFunc,
          cdSupe: perfil.oi[0],
          cdDept: perfil.oi[1],
          cdDivs: perfil.oi[2] ? perfil.oi[2] : " ",
          cdScao: perfil.oi[3] ? perfil.oi[3] : " ",
          cdSetr: perfil.oi[4] ? perfil.oi[4] : " ",
          nivelAcesso: {
            key: {
              cdProj: item.key.cdProj,
              idModu: item.key.idModu,
              cdNivlAces: item.key.cdNivlAces,
            },
          },
        },
      };

      axios
        .post("http://localhost:8082/nivelusuario", payload)
        .then((response) => {
          if (response.status != 201) throw "error";

          this.perfisAssociados.unshift(perfil);
          this.snack = { open: true, text: "Perfil associado" };
        })
        .catch(
          () =>
            (this.snack = {
              open: true,
              text: "Erro :( ",
              color: "red",
            })
        );
    },
    desassociaPerfil(item) {
      console.log(item);
      let payload = {
        key: {
          nrCpf01: this.pessoa.nrCpfFunc01,
          nrCpf02: this.pessoa.nrCpfFunc02,
          nrCpf03: this.pessoa.nrCpfFunc03,
          idDvCpf: this.pessoa.idDvCpfFunc,
          cdSupe: item.oi[0],
          cdDept: item.oi[1],
          cdDivs: item.oi[2] ? item.oi[2] : " ",
          cdScao: item.oi[3] ? item.oi[3] : " ",
          cdSetr: item.oi[4] ? item.oi[4] : " ",
          nivelAcesso: {
            key: {
              cdProj: item.key.cdProj,
              idModu: item.key.idModu,
              cdNivlAces: item.key.cdNivlAces,
            },
          },
        },
      };
      console.log(payload);
      var config = {
        method: "delete",
        url: "http://localhost:8082/nivelusuario",
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(payload),
      };
      axios(config)
        .then((response) => {
          console.log(response);
          if (response.status != 204) throw "error";
          this.perfisAssociados = this.perfisAssociados.filter((f) => {
            return (
              f.oi.concat(f.key.cdNivlAces) !=
              item.oi.concat(item.key.cdNivlAces)
            );
          });
          this.snack = { open: true, text: "Perfil removido" };
        })
        .catch(
          () =>
            (this.snack = {
              open: true,
              text: "Erro :( ",
              color: "red",
            })
        );
    },
    handleChangeModulo(item) {
      const cdProj = item.substring(0, 2);
      const idModu = item.substring(2, 4);
      console.log(cdProj, idModu);
      this.getNivelAcesso(cdProj, idModu);
      this.getNivelAcessoPorPessoa(this.cpf, cdProj, idModu);
    },
    handleInputOi: debounce(function (oi) {
      if (!oi) return;
      console.log(oi);
      this.getOi(oi);
    }, 700),

    getModulos() {
      axios
        .get("http://localhost:8082/modulos")
        .then((response) => {
          this.modulos = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    getPessoaPorCpf(cpf) {
      axios
        .get(`http://localhost:8082/pessoas/findbycpf?cpf=${cpf}`)
        .then((response) => {
          console.log(response.data);
          this.pessoa = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    getNivelAcesso(cdProj, idModu) {
      axios
        .get(
          `http://localhost:8082/nivelacesso?cdProj=${cdProj}&idModu=${idModu}`
        )
        .then((response) => {
          console.log(response.data);
          this.perfis = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    getNivelAcessoPorPessoa(cpf, cdProj, idModu) {
      axios
        .get(
          `http://localhost:8082/nivelusuario/findByCpfProj?cpf=${cpf}&cdProj=${cdProj}&idModu=${idModu}`
        )
        .then((response) => {
          this.perfisAssociados = response.data.map((e) => {
            return {
              codigoNivelAcesso: e.key.nivelAcesso.codigoNivelAcesso,
              key: e.key.nivelAcesso.key,
              oi: e.oi,
            };
          });
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    getOi(oi) {
      axios
        .get(`http://localhost:8082/oi/like?oi=${oi}`)
        .then((response) => {
          console.log(response.data);
          this.listaCompletaOi = response.data;
          this.listaOi = response.data.map((e) => e.oi);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
  created() {
    this.getModulos();
    // this.getPessoaPorCpf('05608377710')
    // this.getNivelAcesso('PV', '01')
    // this.getNivelAcessoPorPessoa('05608377710','PV', '01' )
  },
};
</script>

<style></style>
