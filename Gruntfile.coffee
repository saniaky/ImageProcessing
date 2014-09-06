module.exports = (grunt) ->

  # Constants
  BOWER_PATH = 'bower_components'
  VENDOR_PATH = 'src/main/webapp/vendor'

  # Project configuration.
  grunt.initConfig
    copy:
      build:
        cwd: "#{BOWER_PATH}/",
        src: '**',
        dest: "#{VENDOR_PATH}/",
        expand: true

  # Load the plugin that provides the "uglify" task.
  grunt.loadNpmTasks 'grunt-contrib-copy'

  # Default task(s).
  grunt.registerTask 'default', []
