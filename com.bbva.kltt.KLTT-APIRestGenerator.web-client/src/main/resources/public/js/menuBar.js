
Angular Material
SKIP TO CONTENT
Documentation Version

LATEST RELEASE (1.1.0-RC.5)
TOGGLE COLLAPSED
GETTING STARTED
 
DEMOS
TOGGLE EXPANDED
Autocomplete
Bottom Sheet
Button
Card
Checkbox
Chips
Colors
Content
Datepicker
Dialog
Divider
FAB Speed Dial
FAB Toolbar
Grid List
Icon
Input
List
Menu
Menu Bar current page
Nav Bar
Panel
Progress Circular
Progress Linear
Radio Button
Select
Sidenav
Slider
Subheader
Swipe
Switch
Tabs
Toast
Toolbar
Tooltip
Virtual Repeat
Whiteframe
Customization

CSS
TOGGLE COLLAPSED
 
THEMING
TOGGLE COLLAPSED
API Reference

LAYOUT
TOGGLE COLLAPSED
 
SERVICES
TOGGLE COLLAPSED
 
TYPES
TOGGLE COLLAPSED
 
DIRECTIVES
TOGGLE COLLAPSED
CONTRIBUTORS
Demos -  Menu Bar
Basic Usage


HTMLJSCSSHTMLJSCSS
angular
  .module('menuBarDemoBasic', ['ngMaterial'])
  .config(function($mdIconProvider) {
    $mdIconProvider
      .defaultIconSet('img/icons/sets/core-icons.svg', 24);
  })
  .filter('keyboardShortcut', function($window) {
    return function(str) {
      if (!str) return;
      var keys = str.split('-');
      var isOSX = /Mac OS X/.test($window.navigator.userAgent);
      var seperator = (!isOSX || keys.length > 2) ? '+' : '';
      var abbreviations = {
        M: isOSX ? '⌘' : 'Ctrl',
        A: isOSX ? 'Option' : 'Alt',
        S: 'Shift'
      };
      return keys.map(function(key, index) {
        var last = index == keys.length - 1;
        return last ? key : abbreviations[key];
      }).join(seperator);
    };
  })
  .controller('DemoBasicCtrl', function DemoCtrl($mdDialog) {
    this.settings = {
      printLayout: true,
      showRuler: true,
      showSpellingSuggestions: true,
      presentationMode: 'edit'
    };
    this.sampleAction = function(name, ev) {
      $mdDialog.show($mdDialog.alert()
        .title(name)
        .textContent('You triggered the "' + name + '" action')
        .ok('Great')
        .targetEvent(ev)
      );
    };
  });
Untitled document
File   Edit   View   Format
Untitled document

Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Nullam quis risus eget urna mollis ornare vel eu leo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras mattis consectetur purus sit amet fermentum. Nulla vitae elit libero, a pharetra augue. Nullam id dolor id nibh ultricies vehicula ut id elit. Integer posuere erat a ante venenatis dapibus posuere velit aliquet.

Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Vestibulum id ligula porta felis euismod semper. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Nulla vitae elit libero, a pharetra augue. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum.

Etiam porta sem malesuada magna mollis euismod. Maecenas faucibus mollis interdum. Maecenas sed diam eget risus varius blandit sit amet non magna. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.

Powered by Google ©2014–2016. Code licensed under the MIT License. Documentation licensed under CC BY 4.0.