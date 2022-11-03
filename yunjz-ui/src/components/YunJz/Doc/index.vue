<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'YunJzDoc',
  data() {
    return {
      url: 'http://github.com/XTY-yunsheng'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
