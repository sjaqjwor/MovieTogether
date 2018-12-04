<template>
  <sui-card-group :items-per-row="5">
    <sui-card v-for="movie in movieLIst">
      <sui-card-content>
        {{movie.mname}}
        <sui-card-meta slot="right">{{movie.movierank}}등</sui-card-meta>
      </sui-card-content>
      <sui-image :src="movie.mimageUrl"/>
      <sui-card-content>
        <span slot="right">
          {{movie.maudits}}
        </span>
        {{movie.mgenre}}
      </sui-card-content>
      <sui-card-content extra>
        {{movie.accaudience}} 명
      </sui-card-content>
    </sui-card>
  </sui-card-group>

</template>

<script>
  export default {
    name: 'movieTogehterMain',
    data: function () {
      return {
        movieLIst: []
      }
    },
    created() {
      this.$http.get("http://localhost:8080/movies")
        .then((response) => {
          this.movieLIst = response.data.movieList;
        })
    }

  }
</script>
